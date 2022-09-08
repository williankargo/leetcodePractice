class Solution {
    public long minimumHealth(int[] damage, int armor) {

        // first find max damage so we can apply armor
        int max = damage[0];
        for (int i = 0; i < damage.length; i++) {
            max = Math.max(max, damage[i]);
        }

        long sum = 0;
        for (int i = 0; i < damage.length; i++) {
            sum += damage[i];
        }

        return sum + 1 - Math.min(armor, max);
    }
}