<template>
  <div :class="className" :style="{height:height,width:width}"/>
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import { debounce } from '@/utils'

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
      objects: [],
      jobIds: [],
      cityIds: [],
      titles: ['第一季度（春季招聘）', '第二季度（秋季招聘）', '全年'],
      quarters: ['SPRING', 'AUTUMN', 'ALL']
    }
  },
  mounted() {
    this.initChart();
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
    setOptions({dataMap, legend, selected, city, series, options} = {}) {
      this.chart.setOption({
        baseOption: {
          timeline: {
            // y: 0,
            axisType: 'category',
            // realtime: false,
            // loop: false,
            autoPlay: true,
            // currentIndex: 2,
            playInterval: 5000,
            // controlStyle: {
            //     position: 'left'
            // },
            data: [
              '第一季度（春季招聘）','第二季度（秋季招聘）',
              {
                value: '全年',
                tooltip: {
                  formatter: function (params) {
                    return params.name + '达到又一个高度';
                  }
                },
                symbol: 'diamond',
                symbolSize: 18
              },
            ],
            label: {
              formatter : function(s) {
                return s;
              }
            }
          },
          title: {
            subtext: '数据来自Zhaoping'
          },
          tooltip: {
          },
          legend: {
            x: 'right',
            data: legend,
            selected: selected
          },
          calculable : true,
          grid: {
            top: 80,
            bottom: 100,
            tooltip: {
              trigger: 'axis',
              axisPointer: {
                type: 'shadow',
                label: {
                  show: true,
                  formatter: function (params) {
                    return params.value.replace('\n', '');
                  }
                }
              }
            }
          },
          xAxis: [
            {
              'type':'category',
              'axisLabel':{'interval':0},
              'data': city,
              splitLine: {show: false}
            }
          ],
          yAxis: [
            {
              type: 'value',
              name: '薪资（元）'
            }
          ],
          series: series
        },
        options: options
      })
    },
    initChart() {
      this.lineChartData = {
        legend : [],
        city: [],
        dataMap : {},
        selected: {},
        series: [],
        options: []
      }
      this.$store.dispatch('getJobsName').then((res) => {
        for (var i=0; i < res.data.length; i++) {
          this.lineChartData.legend[i] = res.data[i].jobLabel;
          this.jobIds[i] = res.data[i].id;
        }
        this.$store.dispatch('getCitys').then((res) => {
          for (var i=0; i < res.data.length; i++) {
            this.lineChartData.city[i] = res.data[i].cityLabel;
            this.cityIds[i] = res.data[i].id;
          }
          // 初始化selected属性
          for (var i = 3; i< this.lineChartData.legend.length; i++) {
            var str = this.lineChartData.legend[i];
            this.lineChartData.selected[str] = false
          }
          //初始化city
          for (var i = 0; i < this.lineChartData.city.length; i++) {
            if (i % 2 !== 0) {
              var str = "\n" + this.lineChartData.city[i];
              this.lineChartData.city[i] = str;
            }
          }
          // 初始化series
          for (var i = 0; i < this.lineChartData.legend.length; i++) {
            var object = {type: 'bar'};
            object.name = this.lineChartData.legend[i];
            this.lineChartData.series[i] = object;
          }
          //初始化object
          for (var i = 0; i < this.titles.length; i++) {
            this.objects[i] = { title: {text: this.titles[i]}};
            this.objects[i].series = [];
          }
          for (var i = 0; i < this.lineChartData.legend.length; i++) {
            this.handleData(i);
          }
        })
      });
    },
    dataFormatter(obj) {
      var pList = this.lineChartData.city;
      var temp;
      for (var year = 1; year <= 3; year++) {
        var max = 0;
        var sum = 0;
        temp = obj[year];
        for (var i = 0, l = temp.length; i < l; i++) {
          max = Math.max(max, temp[i]);
          sum += temp[i];
          obj[year][i] = {
            name : pList[i],
            value : temp[i]
          }
        }
        obj[year + 'max'] = Math.floor(max / 100) * 100;
        obj[year + 'sum'] = sum;
      }
      return obj;
    },
    handleData(index) {
      // 初始化dataMap
      var str = 'data' + this.lineChartData.legend[index];
      var jobNameId = this.jobIds[index];
      this.$store.dispatch("getJobsSalaryByCity", { jobNameId: jobNameId, cityIds:this.cityIds,
        quarters:this.quarters }).then((res) => {
        var dataMap = res.data;
        this.lineChartData.dataMap[str] = this.dataFormatter(dataMap);
        for (var i = 0; i < this.titles.length; i++) {
          var dataSeries = {};
          dataSeries.data = this.lineChartData.dataMap[str][i+1];
          this.objects[i].series[index] = dataSeries;
          this.lineChartData.options[i] = this.objects[i];
        }
        this.chart = echarts.init(this.$el);
        this.setOptions(this.lineChartData);
      });
    }
  }
}
</script>
