import Cookies from 'js-cookie'

const TokenKey = 'Admin-Token'
const NameKey = 'Admin-Name'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, process.env.TOKEN_PREFIX + token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

export function setName(name) {
  return Cookies.set(NameKey, name)
}

export function getName() {
  return Cookies.get(NameKey)
}

export function removeName() {
  return Cookies.remove(NameKey)
}
