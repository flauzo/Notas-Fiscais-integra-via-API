package alpe.bruno.notafiscalapi.shared.utils;

import alpe.bruno.notafiscalapi.domain.enums.FormaPagamento;
import alpe.bruno.notafiscalapi.domain.enums.StatusNotaFiscal;
import alpe.bruno.notafiscalapi.domain.model.NotaFiscal;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author brunoabneves
 */
@Service
public class XMLParserService {

    public static NotaFiscal parseXML(MultipartFile file) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file.getInputStream());

        Element root = doc.getDocumentElement();

        return mapearNotaFiscal(root);
    }

    private static NotaFiscal mapearNotaFiscal(Element element) {
        NotaFiscal notaFiscal = new NotaFiscal();
        notaFiscal.setNumero(element.getAttribute("numero"));
        notaFiscal.setCnpjEmissor(element.getAttribute("cnpjEmissor"));
        notaFiscal.setNomeEmissor(element.getAttribute("nomeEmissor"));

        String dataOperacaoStr = element.getAttribute("dataOperacao");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime dataOperacao = LocalDateTime.parse(dataOperacaoStr, formatter);
        notaFiscal.setDataOperacao(dataOperacao);

        String valorTotalStr = element.getAttribute("valorTotal");
        BigDecimal valorTotal = new BigDecimal(valorTotalStr);
        notaFiscal.setValorTotal(valorTotal);

        notaFiscal.setDescricao(element.getAttribute("descricao"));
        notaFiscal.setClienteUuid(UUID.fromString(element.getAttribute("clienteUuid")));
        notaFiscal.setStatus(StatusNotaFiscal.valueOf(element.getAttribute("status")));
        notaFiscal.setObservacao(element.getAttribute("observacao"));

//        NodeList itensNodeList = element.getElementsByTagName("item");
//        List<UUID> itensUuids = new ArrayList<>();
//        for (int i = 0; i < itensNodeList.getLength(); i++) {
//            Element itemElement = (Element) itensNodeList.item(i);
//            UUID itemUuid = UUID.fromString(itemElement.getAttribute("uuid"));
//            itensUuids.add(itemUuid);
//        }
//        notaFiscal.setItensUuids(itensUuids);
        return notaFiscal;
    }
}
