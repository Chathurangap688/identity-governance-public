/*
 * Copyright (c) 2021, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.multi.attribute.login.resolver.regex.utils;

import org.wso2.carbon.identity.multi.attribute.login.resolver.regex.internal.RegexResolverServiceComponent;
import org.wso2.carbon.user.api.UserStoreException;
import org.wso2.carbon.user.core.UniqueIDUserStoreManager;
import org.wso2.carbon.user.core.UserRealm;
import org.wso2.carbon.user.core.service.RealmService;

/**
 * This is a helper class for regex based user resolve service.
 */
public class UserResolverUtil {

    private UserResolverUtil() {

    }

    public static UserRealm getUserRealm(String tenantDomain) throws UserStoreException {

        RealmService realmService = RegexResolverServiceComponent.getRealmService();
        UserRealm userRealm = null;
        if (realmService != null) {
            int tenantId = RegexResolverServiceComponent.getRealmService().getTenantManager().getTenantId(tenantDomain);
            userRealm = (UserRealm) RegexResolverServiceComponent.getRealmService().getTenantUserRealm(tenantId);
        }
        return userRealm;
    }

    public static UniqueIDUserStoreManager getUserStoreManager(String tenantDomain) throws UserStoreException {

        UserRealm userRealm = getUserRealm(tenantDomain);
        return (UniqueIDUserStoreManager) userRealm.getUserStoreManager();
    }
}
