
// solution: https://www.youtube.com/watch?v=TzMl4Z7pVh8&ab_channel=NeetCodeIO
// TC: O(N)
// SC: O(N)

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {

        int tempLength = 0;
        List<String> tempList = new ArrayList<>();
        List<String> list = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            if (tempLength + tempList.size() + words[i].length() > maxWidth) { // tempList.size() -> " "
                int space = (maxWidth - tempLength) / Math.max(1, (tempList.size() - 1)); // in case to / by zero
                int reminder = (maxWidth - tempLength) % Math.max(1, (tempList.size() - 1));

                // fill up the space
                for (int j = 0; j < Math.max(tempList.size() - 1, 1); j++) {
                    tempList.set(j, tempList.get(j) + " ".repeat(space));
                    if (reminder != 0) {
                        tempList.set(j, tempList.get(j) + " ");
                        reminder--;
                    }
                }

                // add result to list
                list.add(String.join("", tempList)); // way to combine list to String

                // restart
                tempLength = 0;
                tempList = new ArrayList<>();
            }
            tempList.add(words[i]);
            tempLength += words[i].length();
        }

        // Handle the last one
        String lastLine = String.join(" ", tempList);
        int extraSpace = maxWidth - lastLine.length();
        lastLine += " ".repeat(extraSpace);
        list.add(lastLine);

        return list;
    }
}