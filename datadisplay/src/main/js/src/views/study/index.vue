<template>
  <div class="mixin-components-container">
    <el-row>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>职业类别</span>
        </div>
        <div style="margin-bottom:50px;">
          <el-col :span="3" class="text-center" v-for="button in buttons" :key="button.id">
            <button :class="button.color" >{{button.legend}}</button>
          </el-col>
        </div>
      </el-card>
    </el-row>
    <el-row style="background:#fff;padding:16px 16px 0;margin-bottom:32px;">
      <study-cartogram />
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
        buttons: [
          {legend: "Java", id:1, color:"pan-btn blue-btn"},
          {legend: "Android", id:2, color:"pan-btn blue-btn"}
        ],
        colors: ["blue-btn","light-blue-btn","pink-btn","green-btn","tiffany-btn","yellow-btn"]
      }
    },
    mounted() {
      this.initChart()
    },
    methods: {
      initChart() {
        //初始化
        this.$store.dispatch('getJobsName').then((res) => {
          for( var i = 0; i < res.data.length; i++) {
            var object = {};
            object['id'] = res.data[i].id;
            object['legend'] = res.data[i].jobLabel;
            object['color'] = "pan-btn " +  this.colors[i % this.colors.length];
            vm.$set(this.buttons, i, object);
          }
        });
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
