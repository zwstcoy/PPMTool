import React, { Component } from "react";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import { getProjectTask, addProjectTask } from "../../../actions/BacklogAction";
import PropTypes from "prop-types";
import classnames from "classnames";

class UpdateProjectTask extends Component {
  constructor(props) {
    super(props);
    this.state = {
      id: "",
      projectSequence: "",
      summary: "",
      acceptanceCriteria: "",
      status: "",
      priority: "",
      dueDate: "",
      projectIdentifier: "",
      create_At: "",
    };
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }
  componentWillReceiveProps(nextProps) {
    const {
      id,
      projectSequence,
      summary,
      acceptanceCriteria,
      status,
      priority,
      dueDate,
      projectIdentifier,
      create_At,
    } = nextProps.project_task;

    this.setState({
      id,
      projectSequence,
      summary,
      acceptanceCriteria,
      status,
      priority,
      dueDate,
      projectIdentifier,
      create_At,
    });
  }

  componentDidMount() {
    const { backlog_id, pt_id } = this.props.match.params;

    this.props.getProjectTask(backlog_id, pt_id, this.props.history);
  }

  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  onSubmit(e) {
    e.preventDefault();
    const newProjectTask = {
      summary: this.state.summary,
      acceptCriteria: this.state.acceptCriteria,
      status: this.state.status,
      priority: this.state.priority,
      dueDate: this.state.dueDate,
    };
    console.log(newProjectTask);
  }

  render() {
    const { project_task } = this.props;
    const { id } = this.props.match.params;
    const { errors } = this.props;

    return (
      <div>
        <div className="add-PBI">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <Link to={`/projectBoard/${id}`} className="btn btn-light">
                  Back to Project Board
                </Link>
                <h4 className="display-4 text-center">Update Project Task</h4>
                <p className="lead text-center">
                  Project Name: {project_task.projectIdentifier} | Project Code:{" "}
                  {project_task.projectSequence}
                </p>
                <form onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg", {
                        "is-invalid": errors.summary,
                      })}
                      name="summary"
                      placeholder="Project Task summary"
                      value={this.state.summary}
                      onChange={this.onChange}
                    />
                    {errors.summary && (
                      <div className="invalid-feedback">{errors.summary}</div>
                    )}
                  </div>
                  <div className="form-group">
                    <textarea
                      className="form-control form-control-lg"
                      placeholder="Acceptance Criteria"
                      name="acceptCriteria"
                      value={this.state.acceptCriteria}
                      onChange={this.onChange}
                    ></textarea>
                  </div>
                  <h6>Due Date</h6>
                  <div className="form-group">
                    <input
                      type="date"
                      className="form-control form-control-lg"
                      name="dueDate"
                      value={this.state.dueDate}
                      onChange={this.onChange}
                    />
                  </div>
                  <div className="form-group">
                    <select
                      className="form-control form-control-lg"
                      name="priority"
                      value={this.state.priority}
                      onChange={this.onChange}
                    >
                      <option value={0}>Select Priority</option>
                      <option value={1}>High</option>
                      <option value={2}>Medium</option>
                      <option value={3}>Low</option>
                    </select>
                  </div>

                  <div className="form-group">
                    <select
                      className="form-control form-control-lg"
                      name="status"
                      value={this.state.status}
                      onChange={this.onChange}
                    >
                      <option value="">Select Status</option>
                      <option value="TO_DO">TO DO</option>
                      <option value="IN_PROGRESS">IN PROGRESS</option>
                      <option value="DONE">DONE</option>
                    </select>
                  </div>

                  <input
                    type="submit"
                    className="btn btn-primary btn-block mt-4"
                  />
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

UpdateProjectTask.protoType = {
  getProjectTask: PropTypes.func.isRequired,
  project_task: PropTypes.object.isRequired,
  errors: PropTypes.object.isRequired,
};

const mapStateToProps = (state) => ({
  project_task: state.backlog.project_task,
  errors: state.errors,
});

export default connect(mapStateToProps, { getProjectTask })(UpdateProjectTask);
