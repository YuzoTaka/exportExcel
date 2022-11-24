package br.com.feltex.excel;

import br.com.feltex.excel.modelo.DadoCpu;
import br.com.feltex.excel.modelo.DadoRam;
import br.com.feltex.excel.modelo.DadoDisco;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
public class CriaArquivoExcel {

    public void criarArquivo(final String nomeArquivo, final List<DadoCpu> dadosCpu, final List<DadoRam> dadosRam, final List<DadoDisco> dadosDisco) {
        log.info("Gerando o arquivo {}", nomeArquivo);

        try (var workbook = new XSSFWorkbook();
                var outputStream = new FileOutputStream(nomeArquivo)) {
            var planilha = workbook.createSheet("Relatório de dados");
            int numeroDaLinha = 0;

            adicionarCabecalho(planilha, numeroDaLinha++);
            for (int i = 0; i < dadosCpu.size(); i++) {
                var linha = planilha.createRow(numeroDaLinha++);
                adicionarCelula(linha, 2, dadosCpu.get(i).getDtHrColetaCPU());
                adicionarCelula(linha, 2, dadosCpu.get(i).getUsoCpu());
                adicionarCelula(linha, 3, dadosCpu.get(i).getTempCpu());
                adicionarCelula(linha, 4, dadosRam.get(i).getUsoRam());
                adicionarCelula(linha, 5, dadosDisco.get(i).getUsoDisco());

            }
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            log.error("Arquivo não encontrado: {}", nomeArquivo);
        } catch (IOException e) {
            log.error("Erro ao processar o arquivo: {} ", nomeArquivo);
        }
        log.info("Arquivo gerado com sucesso!");
    }

    private void adicionarCabecalho(XSSFSheet planilha, int numeroLinha) {
        numeroLinha = 0;
        var linha = planilha.createRow(numeroLinha);
        adicionarCelula(linha, 1, "Data Hora Coleta");
        adicionarCelula(linha, 2, "Uso Cpu");
        adicionarCelula(linha, 3, "Temperatura Cpu");
        adicionarCelula(linha, 4, "Uso Ram");
        adicionarCelula(linha, 5, "Uso Disco");
    }

    private void adicionarCelula(Row linha, int coluna, String valor) {
        Cell cell = linha.createCell(coluna);
        cell.setCellValue(valor);
    }
}
