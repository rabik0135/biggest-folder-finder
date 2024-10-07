import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        String folderPath = "D:/University";
        File file = new File(folderPath);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println(size);
        //System.out.println(getFolderSize(file));

        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " ms");
        System.out.println(getHumanReadableSize(size));
    }

    public static long getFolderSize(File folder) {
        if (folder.isFile()) {
            return folder.length();
        }

        long sum = 0;
        File[] files = folder.listFiles();
        for (File file : files) {
            sum += getFolderSize(file);
        }
        return sum;
    }

    public static String getHumanReadableSize(long size) {
        int counter = 0;
        while (size > 1024) {
            size = size / 1024;
            counter++;
        }
        size += 1;

        HashMap<Integer, String> map = new HashMap<>() {{
            put(0, "B");

            put(1, "Kb");

            put(2, "Mb");

            put(3, "Gb");

            put(4, "Tb");
        }};

        StringBuilder builder = new StringBuilder();
        builder.append(size).append(map.get(counter));

        return builder.toString();
    }

    public static long getSizeFromHumanReadable(String size) {
        return 0;
    }
}