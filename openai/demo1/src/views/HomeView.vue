<template>
  <div class="home">
    <div style="width: 500px;height: 500px;">
      <div v-for="(item, index) in messageList" :key="index">{{item.type == 1 ? '我' : 'ai'}}: {{item.content}}</div>
    </div>
    <input v-model="message"/><button @click="send">发送</button>
  </div>
</template>

<script>

import OpenAI from "openai";

export default {
  name: 'HomeView',
  data() {
    return {
      openai: {},
      message: '',
      messageList: [],
    }
  },
  mounted() {
    this.openai = new OpenAI({ apiKey: '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', dangerouslyAllowBrowser: true });
  },
  methods: {
    async sendAi() {
      const completion = await this.openai.chat.completions.create({
        messages: [{ role: "system", content: this.message }],
        model: "gpt-3.5-turbo",
      });

      console.log(completion.choices[0].message.content);
      this.messageList = [...this.messageList, {type: 2, content: completion.choices[0].message.content}];
    },
    send(){
      this.messageList.push({type: 1, content: this.message})
      this.sendAi();
      this.message = ''
    },
  }
}
</script>
