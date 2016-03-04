package com.word.play.response;

public class WordPlayResponse {

  final private Integer frequency;
  final private Integer trendingNumber;

  public WordPlayResponse(Integer frequency, Integer trendingNumber) {
    this.frequency = frequency;
    this.trendingNumber = trendingNumber;
  }

  public Integer getFrequency() {
    return this.frequency;
  }

  public Integer getTrendingNumber() {
    return this.trendingNumber;
  }
}
