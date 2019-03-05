<template>
  <div class="dashboard-editor-container">
    <panel-group @handleSetLineChartData="handleSetLineChartData"/>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <position-map :chart-data="lineChartData"/>
    </el-row>
  </div>
</template>

<script>
import PanelGroup from './components/PanelGroup'
import PositionMap from './components/PositionMap'

/* eslint-disable */
const lineChartData = {
  mapData: [
    { name: '青岛', value: 175 },
    { name: '上海', value: 175 },
    { name: '厦门', value: 175 },
    { name: '烟台', value: 175 },
    { name: '福州', value: 175 },
    { name: '宁波', value: 175 },
    { name: '南宁', value: 175 },
    { name: '惠州', value: 175 },
    { name: '广州', value: 175 },
    { name: '太原', value: 175 },
    { name: '昆明', value: 175 },
    { name: '深圳', value: 175 },
    { name: '佛山', value: 175 },
    { name: '大连', value: 175 },
    { name: '沈阳', value: 175 },
    { name: '苏州', value: 175 },
    { name: '长春', value: 175 },
    { name: '南昌', value: 175 },
    { name: '成都', value: 175 },
    { name: '西安', value: 175 },
    { name: '重庆', value: 175 },
    { name: '南京', value: 175 },
    { name: '贵阳', value: 175 },
    { name: '无锡', value: 175 },
    { name: '北京', value: 175 },
    { name: '杭州', value: 175 },
    { name: '济南', value: 175 },
    { name: '天津', value: 175 },
    { name: '郑州', value: 175 },
    { name: '哈尔滨', value: 175 },
    { name: '石家庄', value: 175 },
    { name: '长沙', value: 175 },
    { name: '合肥', value: 175 },
    { name: '武汉', value: 175 }
  ],
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

export default {
  name: 'DashboardAdmin',
  components: {
    PanelGroup,
    PositionMap
  },
  data() {
    return {
      lineChartData: lineChartData
    }
  },
  methods: {
    handleSetLineChartData(type) {
      this.lineChartData = lineChartData[type]
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
  }
}
</style>
