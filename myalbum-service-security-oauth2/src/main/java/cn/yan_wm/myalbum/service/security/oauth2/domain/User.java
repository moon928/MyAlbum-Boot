package cn.yan_wm.myalbum.service.security.oauth2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.*;
import java.util.function.Function;

/**
 * @program: MyAlbum-Boot
 * @description: 自定义UserDetails
 * @author: yan_zt
 * @create: 2020-03-29 15:22
 */
public class User implements UserDetails, CredentialsContainer {
    private static final long serialVersionUID = 500L;
    private static final Log logger = LogFactory.getLog(org.springframework.security.core.userdetails.User.class);
    private Integer id;
    private String username;
    private String password;
    private int status;
    private final Set<GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    public User(Integer id,String username, String password,int status, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        if (id != null && username != null && !"".equals(username) && password != null) {
            this.id = id;
            this.username = username;
            this.password = password;
            this.status = status;
            this.enabled = enabled;
            this.accountNonExpired = accountNonExpired;
            this.credentialsNonExpired = credentialsNonExpired;
            this.accountNonLocked = accountNonLocked;
            this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
        } else {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }
    }

    public User(Integer id,String username, String password,int status, Collection<? extends GrantedAuthority> authorities) {
        this(id,username, password, status,true, true, true, true, authorities);
    }
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public Integer getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public int getStstus() {
        return this.status;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public void eraseCredentials() {
        this.password = null;
    }

    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet(new cn.yan_wm.myalbum.service.security.oauth2.domain.User.AuthorityComparator());
        Iterator var2 = authorities.iterator();

        while(var2.hasNext()) {
            GrantedAuthority grantedAuthority = (GrantedAuthority)var2.next();
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }

    public boolean equals(Object rhs) {
        return rhs instanceof org.springframework.security.core.userdetails.User ? this.username.equals(((cn.yan_wm.myalbum.service.security.oauth2.domain.User)rhs).username) : false;
    }

    public int hashCode() {
        return this.username.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("id: ").append(this.id).append("; ");
        sb.append("Username: ").append(this.username).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("status: ").append(this.status).append("; ");
        sb.append("Enabled: ").append(this.enabled).append("; ");
        sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
        sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
        sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");
        if (!this.authorities.isEmpty()) {
            sb.append("Granted Authorities: ");
            boolean first = true;
            Iterator var3 = this.authorities.iterator();

            while(var3.hasNext()) {
                GrantedAuthority auth = (GrantedAuthority)var3.next();
                if (!first) {
                    sb.append(",");
                }

                first = false;
                sb.append(auth);
            }
        } else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();
    }

    public static cn.yan_wm.myalbum.service.security.oauth2.domain.User.UserBuilder withUsername(String username) {
        return builder().username(username);
    }

    public static cn.yan_wm.myalbum.service.security.oauth2.domain.User.UserBuilder builder() {
        return new cn.yan_wm.myalbum.service.security.oauth2.domain.User.UserBuilder();
    }

    /** @deprecated */
    @Deprecated
    public static cn.yan_wm.myalbum.service.security.oauth2.domain.User.UserBuilder withDefaultPasswordEncoder() {
        logger.warn("User.withDefaultPasswordEncoder() is considered unsafe for production and is only intended for sample applications.");
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        cn.yan_wm.myalbum.service.security.oauth2.domain.User.UserBuilder var10000 = builder();
        encoder.getClass();
        return var10000.passwordEncoder(encoder::encode);
    }

    public static cn.yan_wm.myalbum.service.security.oauth2.domain.User.UserBuilder withUserDetails(User userDetails) {
        return withUsername(userDetails.getUsername()).id(userDetails.getId()).status(userDetails.getStstus()).password(userDetails.getPassword()).accountExpired(!userDetails.isAccountNonExpired()).accountLocked(!userDetails.isAccountNonLocked()).authorities(userDetails.getAuthorities()).credentialsExpired(!userDetails.isCredentialsNonExpired()).disabled(!userDetails.isEnabled());
    }

    public static class UserBuilder {
        private Integer id;
        private String username;
        private String password;
        private int status;
        private List<GrantedAuthority> authorities;
        private boolean accountExpired;
        private boolean accountLocked;
        private boolean credentialsExpired;
        private boolean disabled;
        private Function<String, String> passwordEncoder;

        private UserBuilder() {
            this.passwordEncoder = (password) -> {
                return password;
            };
        }

        public cn.yan_wm.myalbum.service.security.oauth2.domain.User.UserBuilder id(Integer id) {
            Assert.notNull(id, "id cannot be null");
            this.id = id;
            return this;
        }

        public cn.yan_wm.myalbum.service.security.oauth2.domain.User.UserBuilder username(String username) {
            Assert.notNull(username, "username cannot be null");
            this.username = username;
            return this;
        }

        public cn.yan_wm.myalbum.service.security.oauth2.domain.User.UserBuilder password(String password) {
            Assert.notNull(password, "password cannot be null");
            this.password = password;
            return this;
        }

        public cn.yan_wm.myalbum.service.security.oauth2.domain.User.UserBuilder status(int status) {
            Assert.notNull(status, "status cannot be null");
            this.status = status;
            return this;
        }

        public cn.yan_wm.myalbum.service.security.oauth2.domain.User.UserBuilder passwordEncoder(Function<String, String> encoder) {
            Assert.notNull(encoder, "encoder cannot be null");
            this.passwordEncoder = encoder;
            return this;
        }

        public cn.yan_wm.myalbum.service.security.oauth2.domain.User.UserBuilder roles(String... roles) {
            List<GrantedAuthority> authorities = new ArrayList(roles.length);
            String[] var3 = roles;
            int var4 = roles.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String role = var3[var5];
                Assert.isTrue(!role.startsWith("ROLE_"), role + " cannot start with ROLE_ (it is automatically added)");
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }

            return this.authorities((Collection)authorities);
        }

        public cn.yan_wm.myalbum.service.security.oauth2.domain.User.UserBuilder authorities(GrantedAuthority... authorities) {
            return this.authorities((Collection)Arrays.asList(authorities));
        }

        public cn.yan_wm.myalbum.service.security.oauth2.domain.User.UserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = new ArrayList(authorities);
            return this;
        }

        public cn.yan_wm.myalbum.service.security.oauth2.domain.User.UserBuilder authorities(String... authorities) {
            return this.authorities((Collection) AuthorityUtils.createAuthorityList(authorities));
        }

        public cn.yan_wm.myalbum.service.security.oauth2.domain.User.UserBuilder accountExpired(boolean accountExpired) {
            this.accountExpired = accountExpired;
            return this;
        }

        public cn.yan_wm.myalbum.service.security.oauth2.domain.User.UserBuilder accountLocked(boolean accountLocked) {
            this.accountLocked = accountLocked;
            return this;
        }

        public cn.yan_wm.myalbum.service.security.oauth2.domain.User.UserBuilder credentialsExpired(boolean credentialsExpired) {
            this.credentialsExpired = credentialsExpired;
            return this;
        }

        public cn.yan_wm.myalbum.service.security.oauth2.domain.User.UserBuilder disabled(boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public UserDetails build() {
            String encodedPassword = (String)this.passwordEncoder.apply(this.password);
            return new User(this.id,this.username, encodedPassword, this.status,!this.disabled, !this.accountExpired, !this.credentialsExpired, !this.accountLocked, this.authorities);
        }
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
        private static final long serialVersionUID = 500L;

        private AuthorityComparator() {
        }

        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            if (g2.getAuthority() == null) {
                return -1;
            } else {
                return g1.getAuthority() == null ? 1 : g1.getAuthority().compareTo(g2.getAuthority());
            }
        }
    }
}
