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
      codeG = codeG + 1;
      sb.append(codeG);
    }
    return sb.toString();
  }

  public static String generateAccountExtract(
      String accountNumber, String balance, String card, String registrationDate)
      throws IOException {
    String outputFile = "/tmp/report.pdf";
    OutputStream outputStream = null;
    try {
      outputStream = new FileOutputStream(outputFile);
    } catch (FileNotFoundException e) {
      LOGGER.error("FileNotFoundException:"+e.getMessage());
      throw new RuntimeException(e);
    }
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
    String htmlDocument = templateEngine.process("account-template", context);
    ITextRenderer renderer = new ITextRenderer();
    renderer.setDocumentFromString(htmlDocument);
    renderer.layout();
    renderer.createPDF(outputStream);
    LOGGER.info(htmlDocument);
    try {
      outputStream.close();
    } catch (IOException e) {
      LOGGER.error(e.getMessage());
      throw new RuntimeException(e);
    }
    try {
      byte[] fileBytes = readBytesFromFile(outputFile);
      String base64String = Base64.getEncoder().encodeToString(fileBytes);
      return base64String;
    } catch (Exception e) {
      LOGGER.error("getFinalPDF:" + e.getMessage());
      return null;
    }
  }

  private static byte[] readBytesFromFile(String filePath) throws IOException {
    File file = new File(filePath);
    FileInputStream fileInputStream = new FileInputStream(file);
    byte[] fileBytes = new byte[(int) file.length()];
    fileInputStream.read(fileBytes);
    fileInputStream.close();
    return fileBytes;
  }
}
