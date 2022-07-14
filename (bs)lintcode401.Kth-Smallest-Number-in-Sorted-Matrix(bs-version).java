class Solution {

    // binary search version
    // Time: O((column or row) * log(row * column)) -> O(equalOrSmaller * binary
    // search)
    // Space: O(1)
    public int kthSmallest(int[][] matrix, int k) {

        if (matrix == null || matrix.length == 0) {
            return -1;
        }
        int row = matrix.length, column = matrix[0].length;
        // 不能對index進行二分，因為本題的row by row不一定會遞增上去
        int start = matrix[0][0], end = matrix[row - 1][column - 1];

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            // 判斷 <= mid 的元素有幾個
            ResultType result = equalOrSmaller(mid, matrix);
            // 這裡也可以留到start&end判斷再處理(直接併入end = mid)，放這裡減枝用
            if (result.isExisted && result.value == k) {
                return mid;
            }
            if (result.value < k) {
                start = mid;
            } else { // result.value >= k , = 狀況發生於mid不存在於此matrix時
                // = 必須放在這裡，可以防止在找>=k時不會定位到喧賓奪主的值
                // 1 1 1(start) 2(end) 2 2 , find k = 2
                // 不會發上上面這種情況，因為當發現1的k>=2時，end就會到1
                // 找『大於等於k次數的『最小值』（result.value>=k時end = mid）』
                end = mid;
            }
        }

        // 上面思考=要放哪裡，下面這個start end無腦都討論就好了
        // start & end相鄰了，沒法用到mid找到的答案，在這裡找，其中一個會是答案
        // 為什麼是 >=k ，而不是 == k ?
        // 因為可能會有重複的target，而我們在equalOrSmaller計算時會把重複的算進去，
        // 例如 matrix = [6 ,6 ,6] ，我們要求k=1，equalOrSmaller(6, matrix)會是3，
        if (equalOrSmaller(start, matrix).value >= k && equalOrSmaller(start, matrix).isExisted) {
            return start;
        }
        if (equalOrSmaller(end, matrix).value >= k && equalOrSmaller(end, matrix).isExisted) {
            return end;
        }

        return -1;
    }

    // 經典技巧
    private ResultType equalOrSmaller(int mid, int[][] matrix) {

        // 從左下比對，一columnㄧcolumn找到右邊
        // https://www.youtube.com/watch?v=JJUv4DDLSB4
        // o o x
        // o x l
        // x l l
        int row = matrix.length, column = matrix[0].length;
        int count = 0;
        int rowIndex = row - 1, columnIndex = 0;
        boolean isExisted = false;

        while (rowIndex >= 0 && columnIndex < column) {
            if (matrix[rowIndex][columnIndex] == mid) { // 找到的這個中位數真的存在
                isExisted = true;
            }
            if (matrix[rowIndex][columnIndex] <= mid) {
                count += (rowIndex + 1); // 整column吃掉
                columnIndex++; // 往下個column出發
            } else {
                rowIndex--; // 向上一個row比較
            }
        }

        return new ResultType(count, isExisted);
    }

    class ResultType {
        int value;
        boolean isExisted;

        ResultType(int value, boolean isExisted) {
            this.value = value;
            this.isExisted = isExisted;
        }
    }
}