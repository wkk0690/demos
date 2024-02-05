<template>
  <div class="player-container">
    <video ref="myPlayer" id="myVideo"></video>
  </div>
</template>
<script>

import 'video.js/dist/video-js.css'; // 导入video.js的CSS样式
import 'video.js/dist/lang/zh-cn.js'; // 导入中文语言包，根据需要选择其他语言包
import VideoJS from 'video.js';


export default {
  data() {
    return {

    };
  },
  mounted() {
    this.$nextTick(() => {
      const player = new VideoJS(this.$refs.myPlayer);

      // RTMP流地址示例（根据实际情况修改）
      const rtmpUrl = "rtmp://live.hkstv.hk.lxdns.com/live/hks";

      player.src({ type: 'rtmp', src: rtmpUrl }); // 使用'rtmp'作为type，而不是'application/x-mpegURL'
      player.play();
    });
  },
  methods: {

  },
}; </script>
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