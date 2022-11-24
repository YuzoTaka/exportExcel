package br.com.feltex.excel;

import br.com.feltex.excel.modelo.Connection;
import br.com.feltex.excel.modelo.DadoCpu;
import br.com.feltex.excel.modelo.DadoRam;
import br.com.feltex.excel.modelo.DadoDisco;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

class CriaArquivoExcelTest {

    Connection connection = new Connection();
    JdbcTemplate con = connection.getConnection();

    List<DadoCpu> DadosCpu = new ArrayList<>();
    List<DadoRam> DadosRam = new ArrayList<>();
    List<DadoDisco> DadosDisco = new ArrayList<>();

    @BeforeEach
    public void setup() {
        List<DadoCpu> teste = con.query("SELECT id_dado_cpu 'Id', uso_cpu 'UsoCpu', temp_cpu 'TempCpu', concat(left(DATE_FORMAT(data_hora_captura, \"%d/%m/%Y | %H:%i:%s\"),10),\" \",right(DATE_FORMAT(data_hora_captura, \"%d/%m/%Y | %H:%i:%s\"),8)) 'DtHrCapturaCpu' FROM dado_cpu;",
                new BeanPropertyRowMapper<>(DadoCpu.class));

        List<DadoRam> teste1 = con.query("SELECT id_dado_ram 'Id', uso_ram 'UsoRam', concat(left(DATE_FORMAT(data_hora_captura, \"%d/%m/%Y | %H:%i:%s\"),10),\" \",right(DATE_FORMAT(data_hora_captura, \"%d/%m/%Y | %H:%i:%s\"),8)) 'DtHrCapturaRam' FROM dado_ram;",
                new BeanPropertyRowMapper<>(DadoRam.class));

        List<DadoDisco> teste2 = con.query("SELECT id_dado_disco 'Id', uso_disco 'UsoDisco', concat(left(DATE_FORMAT(data_hora_captura, \"%d/%m/%Y | %H:%i:%s\"),10),\" \",right(DATE_FORMAT(data_hora_captura, \"%d/%m/%Y | %H:%i:%s\"),8)) 'DtHrCapturaDisco' FROM dado_disco;",
                new BeanPropertyRowMapper<>(DadoDisco.class));

        for (int i = 0; i < teste.size(); i++) {
            DadosCpu.add(teste.get(i));
        }
        for (int i = 0; i < teste1.size(); i++) {
            DadosRam.add(teste1.get(i));
        }
        for (int i = 0; i < teste2.size(); i++) {
            DadosDisco.add(teste2.get(i));
        }
    }

    @Test
    void criarArquivo() {
        var criaArquivoExcel = new CriaArquivoExcel();
        assertNotNull(criaArquivoExcel);
        criaArquivoExcel.criarArquivo("relatório.xlsx", DadosCpu, DadosRam, DadosDisco);
    }
}
