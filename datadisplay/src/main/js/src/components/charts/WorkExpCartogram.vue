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
      workExpIds:[]
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
    setOptions({legend, selected, series, scope} = {}) {
      this.chart.setOption({
        title: {
          text: '工作经验薪资比较',
          subtext: '数据来自Zhaoping'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          data: legend,
          selected: selected
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'value',
          name: '薪资（元）',
          boundaryGap: [0, 0.01]
        },
        yAxis: {
          type: 'category',
          data: scope
        },
        series: series
      })
    },
    initChart() {
      this.lineChartData = {
        legend: ['Java', 'Android', 'PHP', 'Python', 'Web前端', '人工智能', '大数据', '算法'],
        scope: ['1年以下','1-3年','3-5年','5-10年','10年以上'],
        selected: {},
        series: []
      }
      this.$store.dispatch('getJobsName').then((res) => {
        for (var i = 0; i < res.data.length; i++) {
          this.lineChartData.legend[i] = res.data[i].jobLabel;
          this.jobIds[i] = res.data[i].id;
        }
        this.$store.dispatch('getWorkingExp').then((res) => {
          for (var i = 0; i < res.data.length; i++) {
            this.lineChartData.scope[i] = res.data[i].workingLabel;
            this.workExpIds[i] = res.data[i].id;
          }
          // 初始化selected属性
          for (var i = 3; i< this.lineChartData.legend.length; i++) {
            var str = this.lineChartData.legend[i];
            this.lineChartData.selected[str] = false
          }
          for (var i = 0; i < this.lineChartData.legend.length; i++) {
            this.handleData(i);
          }
        })
      });
    },
    handleData(index) {
      this.$store.dispatch('getJobsSalaryByExp',
        {jobNameId: this.jobIds[index], workingExpIds: this.workExpIds}).then((res) => {
          var object = { type: 'bar'};
          object.name = this.lineChartData.legend[index];
          object.data = res.data;
          this.lineChartData.series[index] = object;
          if (index === this.lineChartData.legend.length - 1) {
            this.chart = echarts.init(this.$el);
            this.setOptions(this.lineChartData);
          }
      })
    }
  }
}
</script>
