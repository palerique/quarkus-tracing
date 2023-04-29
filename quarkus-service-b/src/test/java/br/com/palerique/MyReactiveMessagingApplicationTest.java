package br.com.palerique;

import static org.mockito.Mockito.*;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@QuarkusTest
class MyReactiveMessagingApplicationTest {

  @Inject MyReactiveMessagingApplication application;

  @Mock Message<String> message;

  @Test
  void testReceiveMessage() {
    MockitoAnnotations.openMocks(this);

    when(message.getPayload()).thenReturn("test payload");
    when(message.ack()).thenReturn(CompletableFuture.completedFuture(null));

    CompletionStage<Void> result = application.receiveMessage(message);
    verify(message, times(1)).getPayload();
    verify(message, times(1)).ack();
  }
}
