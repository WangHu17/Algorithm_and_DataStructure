package bitoperation;

/**
 * @author wanghu
 * @discrption： 位图的实现
 * @create 2022-05-20 9:30
 */
public class BitMap {

    private long[] bitMap;

    public BitMap(int max){
        bitMap = new long[(max + 64) >> 6];
    }

    public void add(int num){
        bitMap[num >> 6] |= (1L << (num & 63));
    }

    public void delete(int num){
        bitMap[num >> 6] &= ~(1L << (num & 63));
    }

    public boolean contains(int num){
        return (bitMap[num >> 6] & (1L << (num & 63))) != 0;
    }

}
