package com.nxp.appxplorer.marketservices.ci.realm;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleRole;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BearerTokenRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(BearerTokenRealm.class);

    protected final Map<String, SimpleAccount> users;
    protected final Map<String, SimpleRole> roles;
    protected final ReadWriteLock USERS_LOCK;
    protected final ReadWriteLock ROLES_LOCK;

    public BearerTokenRealm() {
	// //this makes the supports(...) method return true only if the token
	// is an instanceof BAT:

	this.users = new LinkedHashMap();
	this.roles = new LinkedHashMap();
	this.USERS_LOCK = new ReentrantReadWriteLock();
	this.ROLES_LOCK = new ReentrantReadWriteLock();

	setCachingEnabled(true);
	setAuthenticationTokenClass(BearerAuthenticationToken.class);
    }

    protected SimpleAccount getUser(String username) {
	this.USERS_LOCK.readLock().lock();
	try {
	    return (SimpleAccount) this.users.get(username);
	} finally {
	    this.USERS_LOCK.readLock().unlock();
	}
    }

    public boolean accountExists(String username) {
	return getUser(username) != null;
    }

    public void addAccount(String username, String password) {
	addAccount(username, password, (String[]) null);
    }

    public void addAccount(String username, String password, String[] roles) {
	Set roleNames = CollectionUtils.asSet(roles);
	SimpleAccount account = new SimpleAccount(username, password, getName(), roleNames, null);
	add(account);
    }

    protected void add(SimpleAccount account) {
	String username = getUsername(account);
	this.USERS_LOCK.writeLock().lock();
	try {
	    this.users.put(username, account);
	} finally {
	    this.USERS_LOCK.writeLock().unlock();
	}
    }

    protected SimpleRole getRole(String rolename) {
	this.ROLES_LOCK.readLock().lock();
	try {
	    return (SimpleRole) this.roles.get(rolename);
	} finally {
	    this.ROLES_LOCK.readLock().unlock();
	}
    }

    public boolean roleExists(String name) {
	return getRole(name) != null;
    }

    public void addRole(String name) {
	add(new SimpleRole(name));
    }

    protected void add(SimpleRole role) {
	this.ROLES_LOCK.writeLock().lock();
	try {
	    this.roles.put(role.getName(), role);
	} finally {
	    this.ROLES_LOCK.writeLock().unlock();
	}
    }

    protected static Set<String> toSet(String delimited, String delimiter) {
	if ((delimited == null) || ("".equals(delimited.trim()))) {
	    return null;
	}

	Set values = new HashSet();
	String[] rolenamesArray = delimited.split(delimiter);
	for (String s : rolenamesArray) {
	    String trimmed = s.trim();
	    if (trimmed.length() > 0) {
		values.add(trimmed);
	    }
	}

	return values;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
	BearerAuthenticationToken bearerToken = (BearerAuthenticationToken) token;
	String bearerToken1 = bearerToken.getBearerToken();
	LOGGER.info("#Got the bearer token:" + bearerToken1);
	return getUser(bearerToken.getUserName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
	String username = getUsername(principals);
	this.USERS_LOCK.readLock().lock();
	try {
	    return (AuthorizationInfo) this.users.get(username);
	} finally {
	    this.USERS_LOCK.readLock().unlock();
	}
    }

    protected String getUsername(SimpleAccount account) {
	return getUsername(account.getPrincipals());
    }

    protected String getUsername(PrincipalCollection principals) {
	return getAvailablePrincipal(principals).toString();
    }

    protected Object getAvailablePrincipal(PrincipalCollection principals) {
	Object primary = null;
	if (!(CollectionUtils.isEmpty(principals))) {
	    Collection thisPrincipals = principals.fromRealm(getName());
	    if (!(CollectionUtils.isEmpty(thisPrincipals))) {
		primary = thisPrincipals.iterator().next();
	    } else {
		primary = principals.getPrimaryPrincipal();
	    }
	}

	return primary;
    }

}
