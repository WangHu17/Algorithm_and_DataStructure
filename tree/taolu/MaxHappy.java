package tree.taolu;

/**
 * @author wanghu
 * @discrption：
 * @create 2022-06-11 16:35
 */
public class MaxHappy {

    class Employee {
        int happy;
        Employee[] nexts;
        public Employee(int happy, Employee[] nexts) {
            this.happy = happy;
            this.nexts = nexts;
        }
    }

    public int maxHappy(Employee root){
        Info info = process(root);
        return Math.max(info.yes, info.no);
    }

    class Info {
        int yes;
        int no;
        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    public Info process(Employee emp){
        if (emp == null)
            return new Info(0, 0);
        int yes = emp.happy;
        int no = 0;
        for (Employee next: emp.nexts){
            Info nextInfo = process(next);
            no += Math.max(nextInfo.yes, nextInfo.no);
            yes += nextInfo.no;
        }
        return new Info(yes, no);
    }

}
