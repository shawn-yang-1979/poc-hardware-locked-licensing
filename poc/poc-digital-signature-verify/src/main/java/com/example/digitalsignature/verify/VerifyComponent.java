package com.example.digitalsignature.verify;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.digitalsignature.properties.DigitalSignatureProperties;

/**
 * This example copy from:
 * https://www.mkyong.com/java/java-digital-signatures-example/
 * 
 * @author SHAWN.SH.YANG
 *
 */
@Component
public class VerifyComponent {

	@Autowired
	private DigitalSignatureProperties digitalSignatureProperties;

	public boolean verify(String text, String fingerprintOfText, String publicKeyPath) throws NoSuchAlgorithmException,
			InvalidKeyException, InvalidKeySpecException, IOException, SignatureException {
		PublicKey publicKey = getPublicKey(publicKeyPath);
		Signature sig = Signature.getInstance(digitalSignatureProperties.getSignatureAlgorithm());
		sig.initVerify(publicKey);
		sig.update(text.getBytes());
		return sig.verify(Base64.getDecoder().decode(fingerprintOfText));
	}

	public PublicKey getPublicKey(String publicKeyPath)
			throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] keyBytes = FileUtils.readFileToByteArray(new File(publicKeyPath));
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory kf = KeyFactory.getInstance(digitalSignatureProperties.getKeyFactoryAlgorithm());
		return kf.generatePublic(spec);
	}

}
