<template>
  <div class="chat-container">
    <div class="chat-header">Chat</div>
    <div class="chat-messages">
      <div class="message" v-for="message in messages" :key="message.id">
        <div class="message-info">
          <span class="message-sender">{{ message.sender }}</span>
          <span class="message-timestamp">{{ message.timestamp }}</span>
        </div>
        <div class="message-content">{{ message.content }}</div>
      </div>
    </div>
    <div class="chat-input">
      <el-input v-model="inputMessage" placeholder="Type your message..." clearable></el-input>
      <el-button type="primary" @click="sendMessage">Send</el-button>
    </div>
  </div>
</template>

<script>
import OpenAI from "openai";
export default {
  data() {
    return {
      openai: {},
      message: '',
      messages: [
      ],
      inputMessage: ''
    };
  },
  mounted() {
    this.openai = new OpenAI({ apiKey: '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', dangerouslyAllowBrowser: true });
  },
  methods: {
    async sendAi({content}) {
      const completion = await this.openai.chat.completions.create({
        messages: [{ role: "system", content: content }],
        model: "gpt-3.5-turbo",
      });
      console.log(completion.choices[0].message.content);
      this.messages = [...this.messages, {
        id: this.messages.length + 1,
        sender: 'ai',
        timestamp: new Date().toLocaleTimeString(),
        content: completion.choices[0].message.content}];
    },
    sendMessage() {
      if (this.inputMessage) {
        const newMessage = {
          id: this.messages.length + 1,
          sender: 'You',
          timestamp: new Date().toLocaleTimeString(),
          content: this.inputMessage
        };
        this.messages.push(newMessage);
        this.inputMessage = '';
        this.sendAi(newMessage);
      }
    }
  }
};
</script>

<style scoped>
.chat-container {
  height: 100vh;
  display: flex;
  flex-direction: column;
}

.chat-header {
  background-color: #409eff;
  color: white;
  padding: 10px;
  font-size: 20px;
  text-align: center;
}

.chat-messages {
  flex-grow: 1;
  overflow-y: scroll;
  padding: 10px;
}

.message {
  margin-bottom: 10px;
}

.message-info {
  display: flex;
  justify-content: space-between;
  margin-bottom: 5px;
  font-size: 14px;
}

.message-sender {
  font-weight: 600;
}

.message-timestamp {
  color: gray;
}

.message-content {
  background-color: #f0f0f0;
  padding: 10px;
  border-radius: 5px;
  word-wrap: break-word;
}

.chat-input {
  display: flex;
  align-items: center;
  padding: 10px;
}

.el-button {
  margin-left: 10px;
}
</style>