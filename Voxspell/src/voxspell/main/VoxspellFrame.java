package voxspell.main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sun.audio.*;
import voxspell.media.audio.Music;

public class VoxspellFrame extends JFrame {
	private VoxspellPanel _voxspellPanel = new VoxspellPanel();
	//private Music _music = new Music();
	
	public static void main(String[] args) {
		Settings.getInstance();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VoxspellFrame frame = new VoxspellFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public VoxspellFrame() {
		super("VOXSPELL (prototype)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);;
		setSize(850,500);
		setVisible(true);
		setContentPane(_voxspellPanel);
	}
	
	private void startup(){
		//TODO
	}
}
