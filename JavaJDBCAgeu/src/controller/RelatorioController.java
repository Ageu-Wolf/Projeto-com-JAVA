
package controller;

import java.util.HashMap;
import java.util.Map;
import connection.ConnectionFactory;

public class RelatorioController {

    public void gerarCarteiraVacinacao(int idPaciente) {
        try {
            JasperReport relatorio = JasperCompileManager.compileReport("CarteiraVacinacao.jrxml");
            Map<String, Object> parametros = new HashMap<>();
            parametros.put("idPaciente", idPaciente);
            JasperPrint print = JasperFillManager.fillReport(relatorio, parametros, ConnectionFactory.getConnection());
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void gerarRelatorioGeral() {
        try {
            JasperReport relatorio = JasperCompileManager.compileReport("RelatorioGeralAplicacoes.jrxml");
            JasperPrint print = JasperFillManager.fillReport(relatorio, null, ConnectionFactory.getConnection());
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
