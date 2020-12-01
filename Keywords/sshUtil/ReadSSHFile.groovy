package sshUtil

//import org.eclipse.persistence.sessions.Session

import com.kms.katalon.core.annotation.Keyword

import io.netty.channel.Channel

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadSSHFile {

	@Keyword
	public void readSsh(){

		JSch jsch = new JSch();
		Session session = null;
		try {
			session = jsch.getSession("fitim", "127.0.0.1", 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword("password");
			session.connect();

			Channel channel = session.openChannel("sftp");
			channel.connect();
			ChannelSftp sftpChannel = (ChannelSftp) channel;

			InputStream stream = sftpChannel.get("C:/Users/fitim/Desktop/data/myinifile.ini");
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(stream));
				String line;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
			} catch (IOException io) {
				System.out.println("Exception occurred during reading file from SFTP server due to " + io.getMessage());
				io.getMessage();
			} catch (Exception e) {
				System.out.println("Exception occurred during reading file from SFTP server due to " + e.getMessage());
				e.getMessage();
			}

			sftpChannel.exit();
			session.disconnect();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		}
	}
}
