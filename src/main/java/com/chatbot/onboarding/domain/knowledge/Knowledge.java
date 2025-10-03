package com.chatbot.onboarding.domain.knowledge;

public class Knowledge {

        private Long id;
        private String question;
        private String response;

        public Knowledge() {

        }

        public Knowledge(String question, String response) {
            this.question = question;
            this.response = response;
        }

         public Long getId() {
            return id;
         }

        public void setId(Long id) {
            this.id = id;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getResponse() {
            return response;
        }

        public void setResponse(String response) {
            this.response = response;
        }
}
