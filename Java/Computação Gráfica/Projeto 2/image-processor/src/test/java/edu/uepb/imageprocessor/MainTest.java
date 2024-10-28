package edu.uepb.imageprocessor;

import edu.uepb.imageprocessor.models.ImageLoader;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MainTest {

    @Test
    public void testLoadAndSavePGM() {
        // Caminhos de entrada e saída
        String inputPath = "images/lena.pgm";
        String outputPath = "images/output/lena_copy_test.pgm";
        
        try {
            // Carregar imagem e verificar se não é nula
            BufferedImage image = ImageLoader.loadPGM(inputPath);
            assertNotNull(image, "Imagem não foi carregada corretamente.");

            // Salvar imagem e verificar se o arquivo de saída foi criado
            ImageLoader.savePGM(image, outputPath);
            File outputFile = new File(outputPath);
            assertTrue(outputFile.exists(), "Imagem não foi salva corretamente.");

            // Apagar o arquivo de teste após verificação para não acumular arquivos
            outputFile.delete();

        } catch (IOException e) {
            e.printStackTrace();
            assertTrue(false, "Erro ao carregar ou salvar a imagem: " + e.getMessage());
        }
    }
}
