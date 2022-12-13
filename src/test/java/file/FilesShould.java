package file;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class FilesShould {

    @Test
    void compare_files() throws URISyntaxException, IOException {
        Path pathFile1 = Path.of(Objects.requireNonNull(getClass().getClassLoader().getResource("file1.txt")).toURI());
        Path pathFile2 = Path.of(Objects.requireNonNull(getClass().getClassLoader().getResource("file2.txt")).toURI());
        Path pathFile3 = Path.of(Objects.requireNonNull(getClass().getClassLoader().getResource("file3.txt")).toURI());
        Path pathFile4 = Path.of(Objects.requireNonNull(getClass().getClassLoader().getResource("file4.txt")).toURI());

        Assertions.assertThat(Files.mismatch(pathFile1, pathFile2)).isEqualTo(-1);
        Assertions.assertThat(Files.mismatch(pathFile1, pathFile3)).isEqualTo(5);
        Assertions.assertThat(Files.mismatch(pathFile3, pathFile4)).isEqualTo(9);
    }
}
