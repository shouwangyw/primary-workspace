import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yangwei
 * @date 2020-06-12 09:28
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class Aaa {
    public static void main(String[] args) {
        System.out.println(getOne("asdfasdfo"));
    }

    private static String getOne(String s) {
        if (s == null || s.length() == 0) {
            return "-1";
        }
        Map<Character, Integer> map = new LinkedHashMap<>();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            map.merge(c, 1, Integer::sum);
            i++;
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return String.valueOf(entry.getKey());
            }
        }
        return "-1";
    }

}
