<template>
  <div :class="className" :style="{height:height,width:width}"/>
</template>

<script>
import echarts from 'echarts'
require('echarts/theme/macarons') // echarts theme
import { debounce } from '@/utils'
require('echarts/extension/bmap/bmap')

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
      geoCoordMap: null,
      cityLabels: [],
      cityIds: [],
      lineChartData: {
        mapData: [],
        geoCoordMap: {
          '青岛':[120.33,36.07],
          '上海':[121.48,31.22],
          '厦门':[118.1,24.46],
          '烟台':[121.39,37.52],
          '福州':[119.3,26.08],
          '宁波':[121.56,29.86],
          '南宁':[108.33,22.84],
          '惠州':[114.4,23.09],
          '广州':[113.23,23.16],
          '太原':[112.53,37.87],
          '昆明':[102.73,25.04],
          '深圳':[114.07,22.62],
          '佛山':[113.11,23.05],
          '大连':[121.62,38.92],
          '沈阳':[123.38,41.8],
          '苏州':[120.62,31.32],
          '长春':[125.35,43.88],
          '南昌':[115.89,28.68],
          '成都':[104.06,30.67],
          '西安':[108.95,34.27],
          '重庆':[106.54,29.59],
          '南京':[118.78,32.04],
          '贵阳':[106.71,26.57],
          '无锡':[120.29,31.59],
          '北京':[116.46,39.92],
          '杭州':[120.19,30.26],
          '济南':[117,36.65],
          '天津':[117.2,39.13],
          '郑州':[113.65,34.76],
          '哈尔滨':[126.63,45.75],
          '石家庄':[114.48,38.03],
          '长沙':[113,28.21],
          '合肥':[117.27,31.86],
          '武汉':[114.31,30.52]
        }
      }
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
    convertData(data) {
      var res = [];
      for (var i = 0; i < data.length; i++) {
        var geoCoord = this.geoCoordMap[data[i].name];
        if (geoCoord) {
          res.push({
            name: data[i].name,
            value: geoCoord.concat(data[i].value)
          });
        }
      }
      return res;
    },
    setOptions({ mapData } = {}) {
      this.chart.setOption({
        // backgroundColor: '#404a59',
        title: {
          text: '全国主要城市职位数',
          subtext: 'data from Zhaopin',
          sublink: 'https://www.zhaopin.com/',
          left: 'center',
          textStyle: {
            color: '#fff'
          }
        },
        tooltip : {
          trigger: 'item'
        },
        bmap: {
          center: [116.46, 39.94],
          zoom: 6,
          roam: true,
          mapStyle: {
            styleJson: [
              {
                "featureType": "water",
                "elementType": "all",
                "stylers": {
                  "color": "#044161"
                }
              },
              {
                "featureType": "land",
                "elementType": "all",
                "stylers": {
                  "color": "#004981"
                }
              },
              {
                "featureType": "boundary",
                "elementType": "geometry",
                "stylers": {
                  "color": "#064f85"
                }
              },
              {
                "featureType": "railway",
                "elementType": "all",
                "stylers": {
                  "visibility": "off"
                }
              },
              {
                "featureType": "highway",
                "elementType": "geometry",
                "stylers": {
                  "color": "#004981"
                }
              },
              {
                "featureType": "highway",
                "elementType": "geometry.fill",
                "stylers": {
                  "color": "#005b96",
                  "lightness": 1
                }
              },
              {
                "featureType": "highway",
                "elementType": "labels",
                "stylers": {
                  "visibility": "off"
                }
              },
              {
                "featureType": "arterial",
                "elementType": "geometry",
                "stylers": {
                  "color": "#004981"
                }
              },
              {
                "featureType": "arterial",
                "elementType": "geometry.fill",
                "stylers": {
                  "color": "#00508b"
                }
              },
              {
                "featureType": "poi",
                "elementType": "all",
                "stylers": {
                  "visibility": "off"
                }
              },
              {
                "featureType": "green",
                "elementType": "all",
                "stylers": {
                  "color": "#056197",
                  "visibility": "off"
                }
              },
              {
                "featureType": "subway",
                "elementType": "all",
                "stylers": {
                  "visibility": "off"
                }
              },
              {
                "featureType": "manmade",
                "elementType": "all",
                "stylers": {
                  "visibility": "off"
                }
              },
              {
                "featureType": "local",
                "elementType": "all",
                "stylers": {
                  "visibility": "off"
                }
              },
              {
                "featureType": "arterial",
                "elementType": "labels",
                "stylers": {
                  "visibility": "off"
                }
              },
              {
                "featureType": "boundary",
                "elementType": "geometry.fill",
                "stylers": {
                  "color": "#029fd4"
                }
              },
              {
                "featureType": "building",
                "elementType": "all",
                "stylers": {
                  "color": "#1a5787"
                }
              },
              {
                "featureType": "label",
                "elementType": "all",
                "stylers": {
                  "visibility": "off"
                }
              }
            ]
          }
        },
        series : [
        /*  {
            name: 'pm2.5',
            type: 'scatter',
            coordinateSystem: 'bmap',
            data: this.convertData(mapData),
            symbolSize: function (val) {
              return val[2] / 10;
            },
            label: {
              normal: {
                formatter: '{b}',
                position: 'right',
                show: false
              },
              emphasis: {
                show: true
              }
            },
            itemStyle: {
              normal: {
                color: '#ddb926'
              }
            }
          },*/
          {
            name: '职位数',
            type: 'effectScatter',
            coordinateSystem: 'bmap',
            data: this.convertData(mapData),
            encode: {
              value: 2
            },
            symbolSize: function (val) {
              return val[2] / 1500;
            },
            showEffectOn: 'emphasis',
            rippleEffect: {
              brushType: 'stroke'
            },
            hoverAnimation: true,
            label: {
              normal: {
                formatter: '{b}',
                position: 'right',
                show: true
              }
            },
            itemStyle: {
              normal: {
                color: '#f4e925',
                shadowBlur: 10,
                shadowColor: '#333'
              }
            },
            zlevel: 1
          },
          /*{
            type: 'custom',
            coordinateSystem: 'bmap',
            renderItem: this.renderItem,
            itemStyle: {
              normal: {
                opacity: 0.5
              }
            },
            animation: false,
            silent: true,
            data: [0],
            z: -10
          }*/
        ]
      })
    },
    initChart() {
      this.$store.dispatch('getCitys').then((res) => {
        for(var i = 0; i < res.data.length; i++) {
          var cityObject = res.data;
          var cityLabel = cityObject[i].cityLabel;
          var cityId = cityObject[i].id;
          this.lineChartData.mapData[i] = { name: cityLabel, value: 175};
          this.cityLabels[i] = cityLabel;
          this.cityIds[i] = cityId
        }
        this.$store.dispatch('getJobsNumByCity', {cityIds:this.cityIds}).then((res) => {
          for(var i = 0; i < res.data.length; i++) {
            var num = res.data[i];
            this.lineChartData.mapData[i].value = num;
          }
          this.geoCoordMap = this.lineChartData.geoCoordMap;
          this.chart = echarts.init(this.$el, 'macarons');
          this.setOptions(this.lineChartData)
        })
      })
    }
  }
}
</script>
