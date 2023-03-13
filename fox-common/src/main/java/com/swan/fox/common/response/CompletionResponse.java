package com.swan.fox.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public   class CompletionResponse {
    public String id;
    public long created;
    public String model;
    public List<Choice> choices;

    public  class Choice {
        public String text;
        public float score;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public float getScore() {
            return score;
        }

        public void setScore(float score) {
            this.score = score;
        }
    }
}