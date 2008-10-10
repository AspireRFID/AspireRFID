/*
 * Copyright 2005-2008, Aspire
 * 
 * This library is free software; you can redistribute it and/or modify it 
 * under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation (the "LGPL"); either version 2.1 of the 
 * License, or (at your option) any later version. If you do not alter this 
 * notice, a recipient may use your version of this file under either the 
 * LGPL version 2.1, or (at his option) any later version.
 * 
 * You should have received a copy of the GNU Lesser General Public License 
 * along with this library; if not, write to the Free Software Foundation, 
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTY OF ANY 
 * KIND, either express or implied. See the GNU Lesser General Public 
 * License for the specific language governing rights and limitations.
 */
package org.ow2.aspirerfid.ons.webservice.beans;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Implementation of the reports ONS Service Remote Bean.
 * 
 * @author François Fornaciari
 * @version 2007
 */
@Stateless
@Remote(ONSServiceRemote.class)
@SuppressWarnings("unchecked")
public class ONSServiceBean implements ONSServiceRemote {

	/**
	 * Entity manager used by this bean.
	 */
	@PersistenceContext
	private EntityManager entityManager = null;

	public String getEPCISServiceURL(String tag) {
		try {
			// Get the EPC Manager Number of the Tag
			String epcManagerNumber = tag.substring(15).split("\\.")[0];
			Query q = entityManager
					.createQuery("select e.serviceURL from EPCManagerBean e "
							+ "where e.epcManagerNumber = :epcManagerNumber");
			q.setParameter("epcManagerNumber", new Integer(epcManagerNumber));
			return (String) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.ons.webservice.beans.ONSServiceRemote#addEPCISService(int,
	 *      java.lang.String, java.lang.String)
	 */
	public void addEPCISService(int epcManagerNumber, String serviceUrl,
			String description) {
		EPCManagerBean epcManagerBean = entityManager.find(
				EPCManagerBean.class, epcManagerNumber);
		if (epcManagerBean == null) {
			epcManagerBean = new EPCManagerBean(serviceUrl, epcManagerNumber,
					description);
			entityManager.persist(epcManagerBean);
		} else {
			epcManagerBean.setDescription(description);
			epcManagerBean.setServiceURL(serviceUrl);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.ons.webservice.beans.ONSServiceRemote#removeEPCISService(int)
	 */
	public void removeEPCISService(int epcManagerNumber) {
		EPCManagerBean epcManagerBean = entityManager.find(
				EPCManagerBean.class, epcManagerNumber);
		if (epcManagerBean != null) {
			entityManager.remove(epcManagerBean);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.ons.webservice.beans.ONSServiceRemote#removeAllEPCISServices()
	 */
	public void removeAllEPCISServices() {
		List<EPCManagerBean> epcManagers = entityManager.createNamedQuery(
				"allEPCManagers").getResultList();
		for (EPCManagerBean epcManager : epcManagers) {
			entityManager.remove(epcManager);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.ons.webservice.beans.ONSServiceRemote#registerEPCISServiceHistory(java.lang.String,
	 *      java.lang.String)
	 */
	public boolean registerEPCISServiceHistory(String tags, String serviceURL) {
		EPCISServiceHistoryBean tagHistoryBean = null;
		tags = tags.replace("[", "");
		tags = tags.replace("]", "");

		String[] tagTab = tags.split(",");
		for (int i = 0; i < tagTab.length; i++) {
			String tag = tagTab[i].trim();
			try {
				Query q = entityManager
						.createQuery("select t from EPCISServiceHistoryBean t "
								+ "where t.tag = :tag and t.serviceURL = :serviceURL");
				q.setParameter("tag", tag);
				q.setParameter("serviceURL", serviceURL);
				tagHistoryBean = (EPCISServiceHistoryBean) q.getSingleResult();
				entityManager.remove(tagHistoryBean);
			} catch (NoResultException e) {
			} finally {
				tagHistoryBean = new EPCISServiceHistoryBean(serviceURL, tag);
				entityManager.persist(tagHistoryBean);
			}
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.ons.webservice.beans.ONSServiceRemote#removeAllEPCISServicesHistories()
	 */
	public void removeAllEPCISServicesHistories() {
		List<EPCISServiceHistoryBean> tagHistories = entityManager
				.createNamedQuery("allEPCISServicesHistories").getResultList();
		for (EPCISServiceHistoryBean tagHistory : tagHistories) {
			entityManager.remove(tagHistory);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.ons.webservice.beans.ONSServiceRemote#removeEPCISServicesHistory(java.lang.String)
	 */
	public void removeEPCISServicesHistory(String tag) {
		try {
			Query q = entityManager
					.createQuery("select t from EPCISServiceHistoryBean t "
							+ "where t.tag = :tag");
			q.setParameter("tag", tag);
			List<EPCISServiceHistoryBean> tagHistoryBeans = q.getResultList();
			for (EPCISServiceHistoryBean tagHistoryBean : tagHistoryBeans) {
				entityManager.remove(tagHistoryBean);
			}
		} catch (NoResultException e) {
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.ons.webservice.beans.ONSServiceRemote#getEPCISServicesHistory(java.lang.String)
	 */
	public String getEPCISServicesHistory(String tag) {
		String result = "";
		try {
			Query q = entityManager
					.createQuery("select t.serviceURL from EPCISServiceHistoryBean t "
							+ "where t.tag = :tag");
			q.setParameter("tag", tag);
			List<String> list = q.getResultList();

			for (int i = 0; i < list.size(); i++) {
				result += list.get(i) + ",";
			}

			if (!result.equals("")) {
				result = result.substring(0, result.length() - 1);
			}
		} catch (NoResultException e) {
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.ow2.aspirerfid.ons.webservice.beans.ONSServiceRemote#getEPCISDescription(java.lang.String)
	 */
	public String getEPCISDescription(String serviceURL) {
		try {
			Query q = entityManager
					.createQuery("select e.description from EPCManagerBean e "
							+ "where e.serviceURL = :serviceURL");
			q.setParameter("serviceURL", serviceURL);
			return (String) q.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
