package limit;

/**
 * @author 木鸢
 * @create by 2017-06-26 22:09
 */
public class Test {

    public static void main(String[] args) throws InterruptedException {

        testTokenBucket();

        testLeakyBucket();
    }

    private static void testLeakyBucket() throws InterruptedException {
        long start = System.currentTimeMillis();
        Limiter leakyBucket = new LeakyBucket(5, 1);
        for (int i = 0; i < 15; i++) {
            boolean acquire = leakyBucket.acquire();
            if(!acquire){
                System.out.println("限流");
            }
            Thread.sleep(500);
        }

        long end = System.currentTimeMillis();
        System.out.println("用时"+ (end-start)+"s");
    }

    private static void testTokenBucket() throws InterruptedException {
        long start = System.currentTimeMillis();
        Limiter tokenBucket = new TokenBucket(5, 1);
        for (int i = 0; i < 15; i++) {
            boolean acquire = tokenBucket.acquire();
            if(!acquire){
                System.out.println("无令牌");
            }
            Thread.sleep(200);
        }

        long end = System.currentTimeMillis();
        System.out.println("用时"+ (end-start)+"s");
    }
}
