package limit;

/**
 * 限流
 *
 * @author 木鸢
 * @create by 2017-06-26 22:08
 */
public interface Limiter {

    public boolean acquire();
}
