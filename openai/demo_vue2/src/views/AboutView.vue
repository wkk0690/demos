<template>
  <div class="chat-wrapper">
    <div class="chat-messages">
      <div class="message" v-for="message in messages" :key="message.id">
        <div class="message-sender">{{ message.sender }}</div>
        <div class="message-content">{{ message.content }}</div>
      </div>
    </div>
    <div class="chat-input"><input type="text" v-model="newMessage" @keyup.enter="sendMessage" placeholder="输入消息...">
      <button @click="sendMessage">发送</button>
    </div>
  </div>
</template>
<script> export default {
  data() {
    return {messages: [], newMessage: "",};
  }, methods: {
    sendMessage() {
      if (this.newMessage) {
        this.messages.push({id: new Date().getTime(), sender: "我", content: this.newMessage,});
        this.newMessage = "";
      }
    },
  },
}; </script>
<style scoped> .chat-wrapper {
  display: flex;
  flex-direction: column;
  height: 100vh;
}

.chat-input {
  display: flex;
  align-items: center;
  padding: 10px;
  background-color: #f1f1f1;
}

input {
  height: 30px;
  flex: 1;
  margin-right: 10px;
  padding: 5px;
  border-radius: 4px;
  border: none;
}

button {
  height: 30px;
  padding: 2px 10px;
  border-radius: 4px;
  border: none;
  background-color: #4caf50;
  color: #fff;
}

.message {
  padding: 10px;
  border-bottom: 1px solid #ccc;
}

.message-sender {
  font-weight: bold;
  margin-bottom: 5px;
}

.message-content {
  word-break: break-all;
} </style>