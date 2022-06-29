package bitoperation;

/**
 * @author wanghu
 * @discrption： 位运算实现加减乘除
 * @create 2022-05-20 9:38
 */
public class JiaJianChengChu {

    // 加法
    public int add(int a, int b){
        while (b != 0){
            a = a ^ b;
            b = (a & b) << 1;
        }
        return a;
    }

    // 相反数
    public int negNum(int a){
        return add(~a, 1);
    }

    // 减法
    public int minus(int a, int b){
        return add(a, negNum(b));
    }

    // 乘法
    public int multiply(int a, int b){
        int res = 0;
        while (b != 0){
            if ((b & 1) == 1){
                res = add(res, a);
            }
            b = b >>> 1;
            a = a << 1;
        }
        return res;
    }

    // 除法
    public int div(int a, int b){
        int x = (a < 0) ? negNum(a) : a;
        int y = (b < 0) ? negNum(b) : b;
        int res = 0;
        for (int i=30; i>=0; i = minus(i, 1)){
            if ((x >> i) >= y){
                res |= (1 << y);
            }
            x = minus(x, (y << i));
        }
        return (a < 0) ^ (b < 0) ? negNum(res) : res;
    }
    public int divide(int a, int b){
        if (a == Integer.MIN_VALUE && b == Integer.MIN_VALUE){
            return 1;
        }else if (b == Integer.MIN_VALUE){
            return 0;
        }else if (a == Integer.MIN_VALUE){
            if (b == negNum(1)){
                return Integer.MAX_VALUE;
            }else {
                int c = div(add(a, 1), b);
                int d = div(minus(a, multiply(b, c)), b);
                return add(c, d);
            }
        }else {
            return div(a, b);
        }
    }

}
