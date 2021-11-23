class SessionController {
  getScheduleVersion() {
    if (localStorage.scheduleVersion) {
      return localStorage.scheduleVersion
    } else {
      return null
    }
  }
  setScheduleVersion(version) {
    localStorage.scheduleVersion = version
  }
}

export default new SessionController()
