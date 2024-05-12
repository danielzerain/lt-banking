package com.ltbanking.account.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.Base64;
import java.util.Random;

public class Utils {
  private static final Logger LOGGER = LoggerFactory.getLogger(Utils.class);


  public static String generateCode(int lengthGenerate) {
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < lengthGenerate; i++) {
      int codeG = random.nextInt(10);
      sb.append(codeG);
    }
    return sb.toString();
  }

  public static String generateAccountExtract(
      String accountNumber, String balance, String card, String registrationDate) {
    OutputStream outputStream = new java.io.ByteArrayOutputStream();
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode(TemplateMode.HTML);
    TemplateEngine templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);
    Context context = new Context();
    context.setVariable("accountNumber", accountNumber);
    context.setVariable("balance", balance);
    context.setVariable("card", card);
    context.setVariable("registrationDate", registrationDate);
    String htmlDocument = templateEngine.process("account-template.html", context);
    ITextRenderer renderer = new ITextRenderer();
    renderer.setDocumentFromString(htmlDocument);
    renderer.layout();
    renderer.createPDF(outputStream);
    LOGGER.info(htmlDocument);
    try {
      outputStream.write(byteArrayOutputStream.toByteArray());
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      return null;
    }
    try {
      outputStream.close();
    } catch (IOException e) {
      LOGGER.error(e.getMessage());
      throw new RuntimeException(e);
    }
    byte[] bytes = byteArrayOutputStream.toByteArray();
    return Base64.getEncoder().encodeToString(bytes);
  }
}
