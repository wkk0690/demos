package com.example.demo.svn;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import java.util.Collection;
import java.util.Iterator;

/**
 * svn日志获取
 * https://wiki.svnkit.com/Printing_Out_Repository_History 代码
 * https://svnkit.com/download.php   //jar包
 */
public class SvnLog {
    public static void main(String[] args) throws SVNException {
        DAVRepositoryFactory.setup();

        String url = "https://192.168.1.149:453/svn/HuaweiPM/Code/xboot-plus-back";
        String name = "wangkaikai";
        String password = "12345678";
        long startRevision = 0;
        long endRevision = -1; //HEAD (the latest) revision
        SVNRepository repository = null;

        repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(name, password);
        repository.setAuthenticationManager(authManager);
        Collection logEntries = null;
        logEntries = repository.log(new String[]{""}, null, startRevision, endRevision, true, true);

        for (Iterator entries = logEntries.iterator(); entries.hasNext(); ) {
            SVNLogEntry logEntry = (SVNLogEntry) entries.next();
            System.out.println("---------------------------------------------");
            System.out.println("revision: " + logEntry.getRevision());
            System.out.println("author: " + logEntry.getAuthor());
            System.out.println("date: " + logEntry.getDate());
            System.out.println("log message: " + logEntry.getMessage());
        }
    }
}
