public class Result implements Comparable<Object> {
    private String word;
    private int times;

    public Result() {
    }

    public Result(String word, int times) {
        // super();
        this.word = word;
        this.times = times;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Result) {
            Result res = (Result) o;
            return res.times - this.times;
        }
        return 0;
    }

    public String getWords() {
        return word;
    }

    public void setWords(String word) {
        this.word = word;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

}
