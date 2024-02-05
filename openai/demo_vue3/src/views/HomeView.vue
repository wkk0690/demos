<template>
  <div class="player-container">
    <video ref="myPlayer" id="myVideo"></video>
  </div>
</template>

<script>

import 'video.js/dist/video-js.css'; // 导入video.js的CSS样式
import VideoJS from 'video.js';


export default {
  name: 'HomeView',
  data() {
    return {

    }
  },
  mounted() {
    setTimeout(()=> {
      const player = new VideoJS(this.$refs.myPlayer);

      // RTMP流地址示例（根据实际情况修改）
      const rtmpUrl = "rtmp://58.200.131.2:1935/livetv/hunantv";

      player.src({ type: 'rtmp', src: rtmpUrl }); // 使用'rtmp'作为type，而不是'application/x-mpegURL'
      player.play();
    }, 1000)
  },
  methods: {

  }
}
</script>
<style scoped>
/* 根据自己项目的情况调整样式 */
.player-container {
  width: 100%;
}
#myVideo {
  width: 100%;
  height: auto;
}
</style>
