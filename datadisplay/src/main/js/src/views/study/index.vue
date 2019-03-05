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
      <study-cartogram :chart-data="lineChartData"/>
    </el-row>
  </div>
</template>

<script>
  /* eslint-disable */
  import StudyCartogram from '@/components/charts/StudyCartogram'

  export default {
    name: 'Study',
    components: {
      StudyCartogram
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
          selected: {},
          series: [],
          scope: ['大专','本科','研究生','博士及以上'],
          chartData: {
            'Java': [2.0, 4.9, 7.0, 23.2],
            'Android': [2.6, 5.9, 9.0, 26.4],
            'PHP': [2.6, 5.9, 9.0, 26.4],
            'Python': [2.6, 5.9, 9.0, 26.4],
            'Web': [2.6, 5.9, 9.0, 26.4],
            'AI': [2.6, 5.9, 9.0, 26.4],
            'BigData': [2.6, 5.9, 9.0, 26.4],
            'Arithmetic': [2.6, 5.9, 9.0, 26.4]
          }
        }
        // 初始化selected属性
        for (var i = 1; i< this.lineChartData.legend.length; i++) {
          var str = this.lineChartData.legend[i];
          this.lineChartData.selected[str] = false
        }

        // 初始化series属性
        for (var i = 0; i < this.types.length; i++) {
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
