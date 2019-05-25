<template>
  <div :class="className" :style="{height:height,width:width}"/>
</template>

<script>
  import echarts from 'echarts'
  require('echarts/theme/macarons') // echarts theme
  import { debounce } from '@/utils'
  import { getExpect } from '@/arithmetic/expect'

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
        lineChartData: {
          chartData: [],
          legends: [],
          times: []
        },
        jobIds: []
      }
    },
    watch: {
      lineChartData: {
        deep: true,
        handler(val) {
          this.setOptions(val)
        }
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
      setOptions({chartData, legends, times} = {}) {
        this.chart.setOption({
          title: {
            text: '本季度招聘热度趋势分析'
          },
          tooltip : {
            trigger: 'axis',
            axisPointer: {
              type: 'cross',
              label: {
                backgroundColor: '#6a7985'
              }
            }
          },
          legend: {
            data: legends
          },
          toolbox: {
            feature: {
              saveAsImage: {}
            }
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis : [
            {
              type : 'category',
              boundaryGap : false,
              data : times
            }
          ],
          yAxis : [
            {
              type : 'value'
            }
          ],
          series : chartData
        })
      },
      initChart() {
        this.$store.dispatch('getJobsName').then((res) => {
          for (var i = 0; i < res.data.length; i++) {
            this.lineChartData.legends[i] = res.data[i].jobLabel;
            this.jobIds[i] = res.data[i].id;
          }
          var today = new Date();
          var month = today.getMonth() + 1;
          if (month <= 6) {
            for (var j = 1; j <= month + 1; j++) {
              this.lineChartData.times[j - 1] = j + "月";
            }
          } else {
            for (var j = 7; j <= month + 1; j++) {
              this.lineChartData.times[j - 7] = j + "月";
            }
          }
          for (var i = 0; i < this.lineChartData.legends.length; i++) {
            this.handleData(i);
          }
        });
        this.chart = echarts.init(this.$el)
        this.setOptions(this.lineChartData)
      },
      handleData(index) {
        this.$store.dispatch("getJobsNumByQuarter", {jobNameId: this.jobIds[index]}).then((res) => {
          var object  = {};
          object['name'] = this.lineChartData.legends[index];
          object['type'] = 'line';
          object['stack'] = '职位数';
          object['areaStyle'] = {};
          var list = res.data;
          list.push(getExpect(res.data, 1, 0.5));
          object['data'] = res.data;
          vm.$set(this.lineChartData.chartData, index, object);
        });
      }
    }
  }
</script>
