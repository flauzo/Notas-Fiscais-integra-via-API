package flauzo.cruz.notafiscalapi.kafka;

import flauzo.cruz.avro.boleto.BoletoAvro;
import flauzo.cruz.notafiscalapi.domain.dto.BoletoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerKafka {

    private static final Logger logger = LoggerFactory.getLogger(ProducerKafka.class);
    private final String topic;
    private final KafkaTemplate<String, BoletoAvro> kafkaTemplate;

    public ProducerKafka(@Value("${mensageria.kafka.topic.boleto}") String topic, KafkaTemplate<String, BoletoAvro> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(BoletoDTO boletoDTO){
        BoletoAvro boletoAvro = toAvro(boletoDTO);
        kafkaTemplate.send(topic, boletoAvro).whenComplete((success, failure) -> {
            if (success != null) {
                logger.info("Mensagem enviada com sucesso: " + success.getProducerRecord().value());
            } else {
                logger.info("Falha ao enviar mensagem: " + failure.getMessage());
            }
        });
    }

    private BoletoAvro toAvro(BoletoDTO dto) {
        return BoletoAvro.newBuilder()
                .setUuid((dto.uuid().toString()))
                .setLinhaDigitavel(dto.linhaDigitavel())
                .setValor(dto.valor().toString())
                .setNumeroDocumento(dto.numeroDocumento())
                .setBeneficiario(dto.beneficiario())
                .setDataVencimento(dto.dataVencimento().toString())
                .build();
    }
}
