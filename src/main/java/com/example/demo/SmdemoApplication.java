package com.example.demo;

import org.bouncycastle.jcajce.provider.asymmetric.EC;
import org.bouncycastle.jcajce.provider.asymmetric.GM;
import org.bouncycastle.jcajce.provider.asymmetric.X509;
import org.bouncycastle.jcajce.provider.digest.SM3;
import org.bouncycastle.jcajce.provider.symmetric.SM4;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.security.*;

@SpringBootApplication
@ImportRuntimeHints(SmdemoApplication.Registrar.class)
public class SmdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmdemoApplication.class, args);
        sm4Test();

    }

    public static void sm4Test() {
        String source = "Hi,This is a SM4Test Native.";
        System.out.println("Source:"+source);
        String str = SM4Util.encrypt(source, "123456");
        System.out.println("Encrypt:"+str);
        System.out.println("Decrypt:"+SM4Util.decrypt(str, "123456"));
    }

    static class Registrar implements RuntimeHintsRegistrar {
        @Override
        public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
            try {
                //no such algorithm: SM3 for provider BC
                hints.reflection().registerType(SM4.class, MemberCategory.INVOKE_DECLARED_METHODS, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS);//好像是mappings
                hints.reflection().registerType(SM4.Mappings.class, MemberCategory.INVOKE_DECLARED_METHODS, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS);//好像是mappings
                hints.reflection().registerType(SM4.ECB.class, MemberCategory.INVOKE_DECLARED_METHODS, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS);//好像是mappings
                hints.reflection().registerType(BouncyCastleProvider.class, MemberCategory.INVOKE_DECLARED_METHODS, MemberCategory.INVOKE_DECLARED_CONSTRUCTORS);//好像是mappings
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
