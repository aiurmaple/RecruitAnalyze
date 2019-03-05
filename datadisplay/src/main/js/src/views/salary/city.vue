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
      <city-cartogram :chart-data="lineChartData"/>
    </el-row>
  </div>
</template>


<script>
  /* eslint-disable */
  import CityCartogram from '@/components/charts/CityCartogram'

  export default {
    name: 'City',
    components: {
      CityCartogram
    },
    data() {
      return {
        lineChartData: null,
        types: ['Java','Android', 'PHP', 'Python', 'Web', 'AI', 'BigData', 'Arithmetic'],
        titles: ['第一季度（春季招聘）', '第二季度（秋季招聘）', '全年']
      }
    },
    mounted() {
      this.initChart()
    },
    methods: {
      initChart() {
        this.lineChartData = {
          legend : ['Java', 'Android', 'PHP', 'Python', 'Web前端', '人工智能', '大数据', '算法'],
          city: ['上海','佛山','北京','南京','南宁','南昌','厦门','合肥',
            '哈尔滨','大连','天津','太原','宁波','广州','惠州','成都',
            '无锡','昆明','杭州','武汉','沈阳','济南','深圳','烟台',
            '石家庄','福州','苏州','西安','贵阳','郑州','重庆','长春',
            '长沙','青岛'],
          dataMap : {},
          selected: {},
          series: [],
          options: []
        }
        // 初始化selected属性
        for (var i = 1; i< this.lineChartData.legend.length; i++) {
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
        // 初始化dataMap
        for (var i = 0; i< this.types.length; i++) {
          var str = 'data' + this.types[i];
          this.lineChartData.dataMap[str] = this.dataFormatter({
            //max : 3600,
            1:[120, 200, 150, 80, 70, 110, 130, 10, 20, 30, 40, 50, 60, 70, 80,
              90, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230, 240
              , 250, 260, 270, 280],
            2:[120, 200, 150, 80, 70, 110, 130, 10, 20, 30, 40, 50, 60, 70, 80,
              90, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230, 240
              , 250, 260, 270, 280],
            3:[120, 200, 150, 80, 70, 110, 130, 10, 20, 30, 40, 50, 60, 70, 80,
              90, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, 210, 220, 230, 240
              , 250, 260, 270, 280]
          });
        }

        // 初始化options
        for (var i = 0; i < this.titles.length; i++) {
          var object = {title: {text: this.titles[i]}}
          object.series = [];
          for (var j =0; j < this.types.length; j++) {
            var dataSeries = {};
            var key = 'data' + this.types[j];
            dataSeries.data = this.lineChartData.dataMap[key][i+1];
            object.series[j] = dataSeries;
          }
          this.lineChartData.options[i] = object;
        }
      },
      dataFormatter(obj) {
        var pList = ['上海', '佛山', '北京', '南京', '南宁', '南昌', '厦门', '合肥', '哈尔滨'
          , '大连', '天津', '太原', '宁波', '广州', '惠州', '成都', '无锡', '昆明', '杭州'
          , '武汉', '沈阳', '济南', '深圳', '烟台', '石家庄', '福州', '苏州', '西安', '贵阳'
          , '郑州', '重庆', '长春', '长沙', '青岛'];
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
      handleSetLineChartData(type) {
        this.lineChartData = lineChartData[type]
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
