package limit;

/**
 * 漏桶算法 水（请求）先进入到漏桶里，漏桶以一定的速度出水，当水流入速度过大会直接溢出，可以看出漏桶算法能强行限制数据的传输速率
 *
 * @author 木鸢
 * @create by 2017-06-26 19:08
 */
public class LeakyBucket implements Limiter {

    int  bucket;        // 桶容量

    int  rate;          // 流出速率

    long lastTimestamp; // 上次请求时间

    int  currentBucket; // 当前量

    public LeakyBucket(int bucket, int rate){
        this.bucket = bucket;
        this.rate = rate;
        this.lastTimestamp = System.currentTimeMillis();
    }

    @Override
    public boolean acquire() {
        long now = System.currentTimeMillis();
        int time = (int) (now - this.lastTimestamp) / 1000;
        this.currentBucket = Math.max(0, currentBucket - (rate * time)); // 先流出
        if (this.currentBucket + 1 <= bucket) {// 判断桶能不能装的下
            this.currentBucket = this.currentBucket + 1;
            this.lastTimestamp = now;
            System.out.println(currentBucket);
            return true;
        }
        return false;
    }

}
