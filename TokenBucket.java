package limit;

/**
 * 令牌桶算法 桶固定容量,以一定速率恒定往桶内装令牌,令牌满了就丢弃 一次请求取一个令牌,令牌为空则限制
 *
 * @author 木鸢
 * @create by 2017-06-26 13:56
 */
public class TokenBucket implements Limiter {

    final int bucket;                                    // 桶容量

    final int rate;                                      // 流入速率[填充令牌的速率,/s 放入几个]

    int       currentBucket = 0;                         // 当前容量

    long      lastTimeStamp = System.currentTimeMillis();

    public TokenBucket(int bucket, int rate){
        this.bucket = bucket;
        this.rate = rate;
        this.currentBucket = bucket;
    }

    public boolean acquire() {
        // 速率 = 容量/时间
        long now = System.currentTimeMillis();
        lastTimeStamp = System.currentTimeMillis();
        
        this. currentBucket= (this.currentBucket + (int) (rate * (now - lastTimeStamp) / 1000));
        if (Math.min(bucket, this.currentBucket) > 0) {
            System.out.println(currentBucket);
            this.currentBucket = this.currentBucket - 1;
            return true;
        }
        return false;
    }

}
