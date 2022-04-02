import java.io.*;
import java.net.URL;
import java.util.List;

public class Downloader {
    public static StringBuilder downloadPicture(List<Picture> list) {
        StringBuilder result = new StringBuilder();
        FileOutputStream out;
        BufferedInputStream in;
        String path = "data" + File.separator + "picture" + File.separator;
        for (Picture picture : list) {
            try {
                in = new BufferedInputStream(new URL(picture.getUrl()).openStream());
                out = new FileOutputStream(path + picture.getName());
                byte[] data = new byte[1024];
                int count;
                while ((count = in.read(data, 0, 1024)) != -1) {
                    out.write(data, 0, count);
                    out.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
                result.append("При скачивании файла произошла ошибка: ").append(picture.getName()).append("\n");
            }
            result.append("Файл успешно скачен: ").append(picture.getName()).append("\n");
        }
        return result;
    }
}
