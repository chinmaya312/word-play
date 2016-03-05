package com.word.play.response;

public class WordPlayResponse {

  final private String word;
  final private Integer frequency;
  final private Integer trendingNumber;

  public WordPlayResponse(String word, Integer frequency, Integer trendingNumber) {
    this.word = word;
    this.frequency = frequency;
    this.trendingNumber = trendingNumber;
  }

  public String getWord() {
    return this.word;
  }

  public Integer getFrequency() {
    return this.frequency;
  }

  public Integer getTrendingNumber() {
    return this.trendingNumber;
  }
}
