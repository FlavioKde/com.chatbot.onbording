package com.chatbot.onboarding;

import com.chatbot.onboarding.application.service.KnowledgeService;
import com.chatbot.onboarding.shared.exception.exceptions.KnowledgeNotFoundException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Profile("!test")
@Component
public class StartupRunner implements CommandLineRunner {

    private final KnowledgeService knowledgeService;

    public StartupRunner(KnowledgeService knowledgeService) {
        this.knowledgeService = knowledgeService;
    }

    @Override
    public void run(String... args) {

        startConsoleInSeparateThread();

        System.out.println("=== Sistema de Onboarding Iniciado ===");
        System.out.println(" API REST disponible en: http://localhost:8080/api");
        System.out.println(" H2 Console disponible en: http://localhost:8080/h2-console");
        System.out.println(" Consola interactiva activa - Escribe tus preguntas abajo:");
        System.out.println();

        if (System.console() != null) {
            System.out.println(" Consola interactiva activa - Escribe tus preguntas abajo:");
            startConsoleInSeparateThread();
        } else {
            System.out.println(" ⚠️ Entrada interactiva no disponible en este entorno.");
            System.out.println(" Podés usar la API REST o la consola H2 para interactuar.");
        }


    }

    private void startConsoleInSeparateThread() {
        Thread consoleThread = new Thread(() -> {
            try {

                Thread.sleep(2000);

                Scanner scanner = new Scanner(System.in);
                System.out.println("Asistente de Onboarding listo. Escribe 'exit' para salir.");


                while (true) {
                    String response = null;
                    String input;

                    do {
                        System.out.print(" Tú: ");
                        input = scanner.nextLine().trim();

                        if (input.equalsIgnoreCase("exit")) {
                            System.out.println("¡Hasta pronto!");
                            scanner.close();
                            return;
                        }

                        if (input.isEmpty()) {
                            System.out.println("La pregunta no puede estar vacía. Intenta de nuevo.");
                            continue;
                        }

                        try {
                            response = knowledgeService.getResponse(input);
                            System.out.println("Bot: " + response);
                                System.out.println();
                            } catch (KnowledgeNotFoundException e) {
                                System.out.println("No se encontró respuesta para: \"" + input + "\"");
                                System.out.println("Intenta con otra pregunta.");
                            }

                                } while (response == null || response.isBlank());
                            }

                        } catch (Exception e) {
                            System.out.println("Error en la consola: " + e.getMessage());
                        }
                    });

            consoleThread.setDaemon(true);
            consoleThread.start();
        }

}
