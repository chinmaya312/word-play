package com.word.play.response;

/**
 * Response Object for rest services.
 */
public class WordPlayResponse {

  private final String word;
  private final Integer frequency;
  private final Integer trendingNumber;

  /**
   * Constructor.
   * @param word word to search
   * @param frequency frequency of the word
   * @param trendingNumber number of times word for searched for
   */
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
