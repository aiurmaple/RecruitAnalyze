<template>
  <div :class="className" :style="{height:height,width:width}"/>
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import { debounce } from '@/utils'

/* eslint-disable */

export default {
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '100%'
    },
    height: {
      type: String,
      default: '670px'
    },
    autoResize: {
      type: Boolean,
      default: true
    },
  },
  data() {
    return {
      chart: null,
      sidebarElm: null,
      lineChartData: null,
      jobIds:[],
      eduIds:[]
    }
  },
  mounted() {
    this.initChart()
    if (this.autoResize) {
      this.__resizeHandler = debounce(() => {
        if (this.chart) {
          this.chart.resize()
        }
      }, 100)
      window.addEventListener('resize', this.__resizeHandler)
    }

    // 监听侧边栏的变化
    this.sidebarElm = document.getElementsByClassName('sidebar-container')[0]
    this.sidebarElm && this.sidebarElm.addEventListener('transitionend', this.sidebarResizeHandler)
  },
  beforeDestroy() {
    if (!this.chart) {
      return
    }
    if (this.autoResize) {
      window.removeEventListener('resize', this.__resizeHandler)
    }

    this.sidebarElm && this.sidebarElm.removeEventListener('transitionend', this.sidebarResizeHandler)

    this.chart.dispose()
    this.chart = null
  },
  methods: {
    sidebarResizeHandler(e) {
      if (e.propertyName === 'width') {
        this.__resizeHandler()
      }
    },
    setOptions({legend, selected, scope, series} = {}) {
      this.chart.setOption({
        title : {
          text: '学历需求比较',
          subtext: '数据来自Zhaoping'
        },
        tooltip : {
          trigger: 'axis'
        },
        legend: {
          data: legend,
          selected: selected
        },
        toolbox: {
          show : true,
          feature : {
            dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
          }
        },
        calculable : true,
        xAxis : [
          {
            type : 'category',
            data : scope
          }
        ],
        yAxis : [
          {
            name: '职位数(个)',
            type : 'value'
          }
        ],
        series : series
      })
    },
    initChart() {
      this.lineChartData = {
        legend: ['Java', 'Android', 'PHP', 'Python', 'Web前端', '人工智能', '大数据', '算法'],
        selected: {},
        series: [],
        scope: ['大专','本科','研究生','博士及以上'],
      }
      this.$store.dispatch('getJobsName').then((res) => {
        for (var i = 0; i < res.data.length; i++) {
          this.lineChartData.legend[i] = res.data[i].jobLabel;
          this.jobIds[i] = res.data[i].id;
        }
        this.$store.dispatch('getEduLevels').then((res) => {
          for (var i = 0; i < res.data.length; i++) {
            this.lineChartData.scope[i] = res.data[i].eduLabel;
            this.eduIds[i] = res.data[i].id;
          }
          // 初始化selected属性
          for (var i = 3; i< this.lineChartData.legend.length; i++) {
            var str = this.lineChartData.legend[i];
            this.lineChartData.selected[str] = false
          }
          for (var i = 0; i < this.lineChartData.legend.length; i++) {
            this.handleData(i);
          }
        });
      });
    },
    handleData(index) {
      this.$store.dispatch('getJobsNumByEdu',
        {jobNameId:this.jobIds[index], eduLevelIds:this.eduIds}).then((res) => {
        var object = {
          type: 'bar',
          markPoint : {
            data : [
              {type : 'max', name: '最大值'},
              {type : 'min', name: '最小值'}
            ]
          },
          markLine : {
            data : [
              {type : 'average', name: '平均值'}
            ]
          }
        };
        object.name = this.lineChartData.legend[index];
        object.data = res.data;
        this.lineChartData.series[index] = object;
          this.chart = echarts.init(this.$el);
          this.setOptions(this.lineChartData)
      });
    }
  }
}
</script>
