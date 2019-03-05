<template>
  <div class="mixin-components-container">
    <el-row>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>职业类别</span>
        </div>
        <div style="margin-bottom:50px;">
          <el-col :span="3" class="text-center">
            <button class="pan-btn blue-btn" >Java</button>
          </el-col>
          <el-col :span="3" class="text-center">
            <button class="pan-btn light-blue-btn" >Android</button>
          </el-col>
          <el-col :span="3" class="text-center">
            <button class="pan-btn pink-btn" >PHP</button>
          </el-col>
          <el-col :span="3" class="text-center">
            <button class="pan-btn green-btn" >Python</button>
          </el-col>
          <el-col :span="3" class="text-center">
            <button class="pan-btn tiffany-btn" >Web前端</button>
          </el-col>
          <el-col :span="3" class="text-center">
            <button class="pan-btn yellow-btn" >人工智能</button>
          </el-col>
          <el-col :span="3" class="text-center">
            <button class="pan-btn pink-btn" >大数据</button>
          </el-col>
          <el-col :span="3" class="text-center">
            <button class="pan-btn blue-btn" >算法</button>
          </el-col>
        </div>
      </el-card>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <work-exp-cartogram :chart-data="lineChartData"/>
    </el-row>
  </div>
</template>

<script>
  /* eslint-disable */
  import WorkExpCartogram from '@/components/charts/WorkExpCartogram'

  export default {
    name: 'WorkExp',
    components: {
      WorkExpCartogram
    },
    data() {
      return {
        lineChartData: null,
        types: ['Java','Android', 'PHP', 'Python', 'Web', 'AI', 'BigData', 'Arithmetic']
      }
    },
    mounted() {
      this.initChart()
    },
    methods: {
      initChart() {
        this.lineChartData = {
          legend: ['Java', 'Android', 'PHP', 'Python', 'Web前端', '人工智能', '大数据', '算法'],
          scope: ['1年以下','1-3年','3-5年','5-10年','10年以上'],
          chartData: {
            'Java': [15203, 18203, 23489, 29034, 32814],
            'Android': [14325, 19325, 23438, 31000, 35000],
            'PHP': [12032, 20325, 25438, 34000, 37000],
            'Python': [15000, 22325, 26438, 36000, 40000],
            'Web': [7213, 10325, 13438, 11000, 15000],
            'AI': [1324, 9325, 3438, 10000, 30000],
            'BigData': [12000, 39325, 33438, 31000, 31415],
            'Arithmetic': [13415, 29325, 27438, 21000, 31391]
          },
          selected: {},
          series: []
        }
        // 初始化selected属性
        for (var i = 1; i< this.lineChartData.legend.length; i++) {
          var str = this.lineChartData.legend[i];
          this.lineChartData.selected[str] = false
        }

        // 初始化series属性
        for(var i = 0; i < this.types.length; i++) {
          var object = { type: 'bar'};
          object.name = this.lineChartData.legend[i];
          object.data = this.lineChartData.chartData[this.types[i]];
          this.lineChartData.series[i] = object;
        }

      }
    }
  }
</script>

<style scoped>
.mixin-components-container {
  background-color: #f0f2f5;
  padding: 30px;
  min-height: calc(100vh - 84px);
}
</style>

<style rel="stylesheet/scss" lang="scss" scoped>
  @import '../../styles/btn';

  .text-center {
    text-align: center
  }
</style>
